package bsep.sa.SiemAgent.readers;

import bsep.sa.SiemAgent.model.Log;
import bsep.sa.SiemAgent.model.LogFile;
import bsep.sa.SiemAgent.model.LogPattern;
import bsep.sa.SiemAgent.service.LogSenderScheduler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.krakens.grok.api.Grok;
import io.krakens.grok.api.GrokCompiler;
import io.krakens.grok.api.Match;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LinuxLogReader implements Runnable {
    private LogFile logFile;
    private LogSenderScheduler logSenderScheduler;

    public LinuxLogReader(LogFile logFile, LogSenderScheduler logSenderScheduler) {
        super();
        this.logFile = logFile;
        this.logSenderScheduler = logSenderScheduler;
    }

    @Override
    public void run() {
        System.out.println("Started reader for" + logFile.getPath());
        try {
            FileReader fr = new FileReader(logFile.getPath());
            BufferedReader br = new BufferedReader(fr);
            jumpToEnd(br);

            while (true) {
                String line = br.readLine();
                if (line == null) {
                    Thread.sleep(logFile.getReadFrequency());
                } else {
                    List<Log> logs = parse(line);
                    logs.stream().forEach(log -> logSenderScheduler.addLog(log));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void jumpToEnd(BufferedReader br) throws IOException {
        while (br.readLine() != null) {}
    }

    public List<Log> parse(String line) {
        Gson gson = new Gson();
        GrokCompiler grokCompiler = GrokCompiler.newInstance();
        grokCompiler.registerDefaultPatterns();

        List<Log> logs = new LinkedList<>();
        Log templateLog = new Log();
        Type typeMap = new TypeToken<Map<String, String>>(){}.getType();

        for (LogPattern logPattern : logFile.getLogPatterns()) {
            Map<String, Object> logMap = gson.fromJson(gson.toJson(templateLog), typeMap);
            Grok grok = grokCompiler.compile(logPattern.getPattern());
            Match gm = grok.match(line);
            Map<String, Object> capturedFields = gm.capture();

            boolean added = false;
            for (String logField : logMap.keySet()) {
                if (capturedFields.containsKey(logField)) {
                    logMap.put(logField, capturedFields.get(logField));
                    added = true;
                }
            }

            if (!added) {
                continue;
            }

            logMap.put("eventType", logPattern.getType());
            logMap.put("eventName", logPattern.getName());
            logMap.put("message", line);

            Log log = gson.fromJson(gson.toJson(logMap, typeMap), Log.class);
            logs.add(log);
        }
        return logs;
    }
}

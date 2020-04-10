import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { RevokeDialogService } from 'src/app/services/revoke-dialog.service';
import { Subscription } from 'rxjs';
import { faArrowDown, faArrowUp } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-view-all-certificates',
  templateUrl: './view-all-certificates.component.html',
  styleUrls: ['./view-all-certificates.component.css']
})
export class ViewAllCertificatesComponent implements OnInit {

  //ICONS
  faArrowDown = faArrowDown;
  faArrowUp = faArrowUp;

  private data: any[] = [];
  private elStatus: any[] = [];

  private subscription: Subscription;

  @Input() isRevoked: boolean;
  @Input() isCA: boolean;

  @Input('parentData') set parentData(parentData) {
    let items = parentData;
    let finalData = []
    let elStatus = []
    for (let i = 0; i < items.length; i++) {
      let root = items[i];
      let rootStatus = {
        isHovered: false,
        isSelected: false,
        extensionsSelected: false,
      }
      if (!this.isRevoked && root.certificateDto.revocation) continue;
      if (!this.isCA && root.caType) continue
      
      if (root.caIssuerId) {
        this.formCertificateData(root, rootStatus, items);
      }
      finalData.push(root);
      elStatus.push(rootStatus);
    }

    this.data = finalData;
    this.elStatus = elStatus;
    console.log(this.elStatus);
  }
  get parentData() { return this.data; }

  
  constructor(private revokeDialogService: RevokeDialogService) { }

  ngOnInit() {
    if (this.isRevoked) return;
    this.subscription = this.revokeDialogService.getData().subscribe(
      data => {
        if (data.revoked) {
          this.data.splice(data.index, 1);
          this.elStatus.splice(data.index, 1);
        }
      }
    );
  }

  ngOnDestroy() {
    if (this.isRevoked) return;
    this.subscription.unsubscribe();
  }

  initRevokeDialog(item: any): void {
    item.open = true;
    this.revokeDialogService.sendData(item);
  }

  formCertificateData(root: any, rootStatus: any, data: any[]): any {
    let issuer = root;
    let issuerStatus = rootStatus;

    
    if (issuer.caIssuerId) {
      issuer.issuer = this.findIssuerById(issuer.caIssuerId, data);
      issuerStatus.issuerStatus = {
        isHovered: false,
        isSelected: false,
        extensionsSelected: false,
      };
      if (issuer.issuer.caIssuerId) {
        this.formCertificateData(issuer.issuer, issuerStatus.issuerStatus, data);
      }
    }

    return issuer;
  }

  toggleExtensions(status) {
    status.extensionsSelected = !status.extensionsSelected;
  }

  findIssuerById(id: number, data: any[]): any {
    for (let i = 0; i < data.length; i++) {
      if (data[i].id == id) {
        return data[i];
      }
    }
    return null;
  }

  mouseEnter(event, index): void {
    this.elStatus[index].isHovered = true;
  }

  mouseLeave(event, index): void {
    this.elStatus[index].isHovered = false;
  }

  moreOrLessInformation(event, index): void {
    this.elStatus[index].isSelected = !this.elStatus[index].isSelected;
    if (!this.elStatus[index].isSelected) {
      this.elStatus[index].isHovered = false;
    }
  }

  moreOrLessIssuerInformation(event, issuerStatus): void {
    issuerStatus.isSelected = !issuerStatus.isSelected;
  }

}

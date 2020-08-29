import { IInstallationKIPiA } from '@/shared/model/installation-ki-pi-a.model';
import { ITender } from '@/shared/model/tender.model';

export interface IInstallationKIPiAGroup {
  id?: number;
  quantity?: number;
  installationKIPiAS?: IInstallationKIPiA[];
  tenders?: ITender[];
}

export class InstallationKIPiAGroup implements IInstallationKIPiAGroup {
  constructor(public id?: number, public quantity?: number, public installationKIPiAS?: IInstallationKIPiA[], public tenders?: ITender[]) {}
}

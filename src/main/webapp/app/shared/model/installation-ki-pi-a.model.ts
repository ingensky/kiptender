import { IInstallationKIPiAGroup } from '@/shared/model/installation-ki-pi-a-group.model';

export interface IInstallationKIPiA {
  id?: number;
  title?: string;
  installationKIPiAGroups?: IInstallationKIPiAGroup[];
}

export class InstallationKIPiA implements IInstallationKIPiA {
  constructor(public id?: number, public title?: string, public installationKIPiAGroups?: IInstallationKIPiAGroup[]) {}
}

import { IProjectMark } from '@/shared/model/project-mark.model';
import { IUnitKIPiAGroup } from '@/shared/model/unit-ki-pi-a-group.model';
import { IInstallationKIPiAGroup } from '@/shared/model/installation-ki-pi-a-group.model';
import { IClient } from '@/shared/model/client.model';

export interface ITender {
  id?: number;
  number?: string;
  title?: string;
  description?: string;
  location?: string;
  worktrip?: boolean;
  needSIDStage?: boolean;
  needOTRStage?: boolean;
  projectMarks?: IProjectMark[];
  unitKIPiAGroups?: IUnitKIPiAGroup[];
  installationKIPiAGroups?: IInstallationKIPiAGroup[];
  client?: IClient;
}

export class Tender implements ITender {
  constructor(
    public id?: number,
    public number?: string,
    public title?: string,
    public description?: string,
    public location?: string,
    public worktrip?: boolean,
    public needSIDStage?: boolean,
    public needOTRStage?: boolean,
    public projectMarks?: IProjectMark[],
    public unitKIPiAGroups?: IUnitKIPiAGroup[],
    public installationKIPiAGroups?: IInstallationKIPiAGroup[],
    public client?: IClient
  ) {
    this.worktrip = this.worktrip || false;
    this.needSIDStage = this.needSIDStage || false;
    this.needOTRStage = this.needOTRStage || false;
  }
}

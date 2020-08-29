import { IKIPWiringDiagramType } from '@/shared/model/kip-wiring-diagram-type.model';
import { IMeasurementParameter } from '@/shared/model/measurement-parameter.model';
import { IUnitKIPiAGroup } from '@/shared/model/unit-ki-pi-a-group.model';

export interface IUnitKIPiA {
  id?: number;
  title?: string;
  description?: string;
  systemRSUPAZ?: boolean;
  rootPlanPresence?: boolean;
  needProjectOrder?: boolean;
  needProjectCalculation?: boolean;
  needElectricalHeating?: boolean;
  numberAI?: number;
  numberAO?: number;
  numberDI?: number;
  numberDO?: number;
  basicLaborInput?: number;
  kipWiringDiagramType?: IKIPWiringDiagramType;
  meashurmentParameter?: IMeasurementParameter;
  unitKIPiAGroups?: IUnitKIPiAGroup[];
}

export class UnitKIPiA implements IUnitKIPiA {
  constructor(
    public id?: number,
    public title?: string,
    public description?: string,
    public systemRSUPAZ?: boolean,
    public rootPlanPresence?: boolean,
    public needProjectOrder?: boolean,
    public needProjectCalculation?: boolean,
    public needElectricalHeating?: boolean,
    public numberAI?: number,
    public numberAO?: number,
    public numberDI?: number,
    public numberDO?: number,
    public basicLaborInput?: number,
    public kipWiringDiagramType?: IKIPWiringDiagramType,
    public meashurmentParameter?: IMeasurementParameter,
    public unitKIPiAGroups?: IUnitKIPiAGroup[]
  ) {
    this.systemRSUPAZ = this.systemRSUPAZ || false;
    this.rootPlanPresence = this.rootPlanPresence || false;
    this.needProjectOrder = this.needProjectOrder || false;
    this.needProjectCalculation = this.needProjectCalculation || false;
    this.needElectricalHeating = this.needElectricalHeating || false;
  }
}

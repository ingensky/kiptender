import { IUnitKIPiA } from '@/shared/model/unit-ki-pi-a.model';
import { ITender } from '@/shared/model/tender.model';

export interface IUnitKIPiAGroup {
  id?: number;
  quantity?: number;
  unitKIPiAS?: IUnitKIPiA[];
  tenders?: ITender[];
}

export class UnitKIPiAGroup implements IUnitKIPiAGroup {
  constructor(public id?: number, public quantity?: number, public unitKIPiAS?: IUnitKIPiA[], public tenders?: ITender[]) {}
}

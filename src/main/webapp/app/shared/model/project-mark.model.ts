import { ITender } from '@/shared/model/tender.model';

export interface IProjectMark {
  id?: number;
  title?: string;
  mark?: string;
  tenders?: ITender[];
}

export class ProjectMark implements IProjectMark {
  constructor(public id?: number, public title?: string, public mark?: string, public tenders?: ITender[]) {}
}

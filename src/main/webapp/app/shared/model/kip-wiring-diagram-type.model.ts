export interface IKIPWiringDiagramType {
  id?: number;
  title?: string;
  description?: string;
}

export class KIPWiringDiagramType implements IKIPWiringDiagramType {
  constructor(public id?: number, public title?: string, public description?: string) {}
}

export interface IMeasurementParameter {
  id?: number;
  title?: string;
}

export class MeasurementParameter implements IMeasurementParameter {
  constructor(public id?: number, public title?: string) {}
}

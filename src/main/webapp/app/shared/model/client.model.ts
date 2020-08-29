import { IUser } from '@/shared/model/user.model';
import { ITender } from '@/shared/model/tender.model';

export interface IClient {
  id?: number;
  title?: string;
  contactName?: string;
  contactNumber?: string;
  description?: string;
  user?: IUser;
  tenders?: ITender[];
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public title?: string,
    public contactName?: string,
    public contactNumber?: string,
    public description?: string,
    public user?: IUser,
    public tenders?: ITender[]
  ) {}
}

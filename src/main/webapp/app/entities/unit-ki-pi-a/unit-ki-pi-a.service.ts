import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IUnitKIPiA } from '@/shared/model/unit-ki-pi-a.model';

const baseApiUrl = 'api/unit-ki-pi-as';

export default class UnitKIPiAService {
  public find(id: number): Promise<IUnitKIPiA> {
    return new Promise<IUnitKIPiA>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(paginationQuery?: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl + `?${buildPaginationQueryOpts(paginationQuery)}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IUnitKIPiA): Promise<IUnitKIPiA> {
    return new Promise<IUnitKIPiA>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: IUnitKIPiA): Promise<IUnitKIPiA> {
    return new Promise<IUnitKIPiA>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}

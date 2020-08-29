import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IKIPWiringDiagramType } from '@/shared/model/kip-wiring-diagram-type.model';

const baseApiUrl = 'api/kip-wiring-diagram-types';

export default class KIPWiringDiagramTypeService {
  public find(id: number): Promise<IKIPWiringDiagramType> {
    return new Promise<IKIPWiringDiagramType>((resolve, reject) => {
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

  public create(entity: IKIPWiringDiagramType): Promise<IKIPWiringDiagramType> {
    return new Promise<IKIPWiringDiagramType>((resolve, reject) => {
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

  public update(entity: IKIPWiringDiagramType): Promise<IKIPWiringDiagramType> {
    return new Promise<IKIPWiringDiagramType>((resolve, reject) => {
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

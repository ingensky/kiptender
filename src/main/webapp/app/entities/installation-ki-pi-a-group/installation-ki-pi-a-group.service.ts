import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IInstallationKIPiAGroup } from '@/shared/model/installation-ki-pi-a-group.model';

const baseApiUrl = 'api/installation-ki-pi-a-groups';

export default class InstallationKIPiAGroupService {
  public find(id: number): Promise<IInstallationKIPiAGroup> {
    return new Promise<IInstallationKIPiAGroup>((resolve, reject) => {
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

  public create(entity: IInstallationKIPiAGroup): Promise<IInstallationKIPiAGroup> {
    return new Promise<IInstallationKIPiAGroup>((resolve, reject) => {
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

  public update(entity: IInstallationKIPiAGroup): Promise<IInstallationKIPiAGroup> {
    return new Promise<IInstallationKIPiAGroup>((resolve, reject) => {
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

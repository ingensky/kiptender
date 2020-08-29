import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import UserService from '@/admin/user-management/user-management.service';

import TenderService from '../tender/tender.service';
import { ITender } from '@/shared/model/tender.model';

import AlertService from '@/shared/alert/alert.service';
import { IClient, Client } from '@/shared/model/client.model';
import ClientService from './client.service';

const validations: any = {
  client: {
    title: {
      required,
      minLength: minLength(3),
    },
    contactName: {
      minLength: minLength(3),
    },
    contactNumber: {
      minLength: minLength(3),
    },
    description: {
      minLength: minLength(3),
    },
  },
};

@Component({
  validations,
})
export default class ClientUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('clientService') private clientService: () => ClientService;
  public client: IClient = new Client();

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];

  @Inject('tenderService') private tenderService: () => TenderService;

  public tenders: ITender[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.clientId) {
        vm.retrieveClient(to.params.clientId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.client.id) {
      this.clientService()
        .update(this.client)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.client.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.clientService()
        .create(this.client)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.client.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveClient(clientId): void {
    this.clientService()
      .find(clientId)
      .then(res => {
        this.client = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.userService()
      .retrieve()
      .then(res => {
        this.users = res.data;
      });
    this.tenderService()
      .retrieve()
      .then(res => {
        this.tenders = res.data;
      });
  }
}

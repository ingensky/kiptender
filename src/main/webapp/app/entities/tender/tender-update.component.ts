import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import ProjectMarkService from '../project-mark/project-mark.service';
import { IProjectMark } from '@/shared/model/project-mark.model';

import UnitKIPiAGroupService from '../unit-ki-pi-a-group/unit-ki-pi-a-group.service';
import { IUnitKIPiAGroup } from '@/shared/model/unit-ki-pi-a-group.model';

import InstallationKIPiAGroupService from '../installation-ki-pi-a-group/installation-ki-pi-a-group.service';
import { IInstallationKIPiAGroup } from '@/shared/model/installation-ki-pi-a-group.model';

import ClientService from '../client/client.service';
import { IClient } from '@/shared/model/client.model';

import AlertService from '@/shared/alert/alert.service';
import { ITender, Tender } from '@/shared/model/tender.model';
import TenderService from './tender.service';

const validations: any = {
  tender: {
    number: {},
    title: {
      required,
      minLength: minLength(3),
    },
    description: {
      minLength: minLength(3),
    },
    location: {},
    worktrip: {},
    needSIDStage: {},
    needOTRStage: {},
  },
};

@Component({
  validations,
})
export default class TenderUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('tenderService') private tenderService: () => TenderService;
  public tender: ITender = new Tender();

  @Inject('projectMarkService') private projectMarkService: () => ProjectMarkService;

  public projectMarks: IProjectMark[] = [];

  @Inject('unitKIPiAGroupService') private unitKIPiAGroupService: () => UnitKIPiAGroupService;

  public unitKIPiAGroups: IUnitKIPiAGroup[] = [];

  @Inject('installationKIPiAGroupService') private installationKIPiAGroupService: () => InstallationKIPiAGroupService;

  public installationKIPiAGroups: IInstallationKIPiAGroup[] = [];

  @Inject('clientService') private clientService: () => ClientService;

  public clients: IClient[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tenderId) {
        vm.retrieveTender(to.params.tenderId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.tender.projectMarks = [];
    this.tender.unitKIPiAGroups = [];
    this.tender.installationKIPiAGroups = [];
  }

  public save(): void {
    this.isSaving = true;
    if (this.tender.id) {
      this.tenderService()
        .update(this.tender)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.tender.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.tenderService()
        .create(this.tender)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.tender.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveTender(tenderId): void {
    this.tenderService()
      .find(tenderId)
      .then(res => {
        this.tender = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.projectMarkService()
      .retrieve()
      .then(res => {
        this.projectMarks = res.data;
      });
    this.unitKIPiAGroupService()
      .retrieve()
      .then(res => {
        this.unitKIPiAGroups = res.data;
      });
    this.installationKIPiAGroupService()
      .retrieve()
      .then(res => {
        this.installationKIPiAGroups = res.data;
      });
    this.clientService()
      .retrieve()
      .then(res => {
        this.clients = res.data;
      });
  }

  public getSelected(selectedVals, option): any {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}

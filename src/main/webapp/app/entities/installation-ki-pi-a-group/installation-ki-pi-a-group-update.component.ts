import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import InstallationKIPiAService from '../installation-ki-pi-a/installation-ki-pi-a.service';
import { IInstallationKIPiA } from '@/shared/model/installation-ki-pi-a.model';

import TenderService from '../tender/tender.service';
import { ITender } from '@/shared/model/tender.model';

import AlertService from '@/shared/alert/alert.service';
import { IInstallationKIPiAGroup, InstallationKIPiAGroup } from '@/shared/model/installation-ki-pi-a-group.model';
import InstallationKIPiAGroupService from './installation-ki-pi-a-group.service';

const validations: any = {
  installationKIPiAGroup: {
    quantity: {},
  },
};

@Component({
  validations,
})
export default class InstallationKIPiAGroupUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('installationKIPiAGroupService') private installationKIPiAGroupService: () => InstallationKIPiAGroupService;
  public installationKIPiAGroup: IInstallationKIPiAGroup = new InstallationKIPiAGroup();

  @Inject('installationKIPiAService') private installationKIPiAService: () => InstallationKIPiAService;

  public installationKIPiAS: IInstallationKIPiA[] = [];

  @Inject('tenderService') private tenderService: () => TenderService;

  public tenders: ITender[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.installationKIPiAGroupId) {
        vm.retrieveInstallationKIPiAGroup(to.params.installationKIPiAGroupId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.installationKIPiAGroup.installationKIPiAS = [];
  }

  public save(): void {
    this.isSaving = true;
    if (this.installationKIPiAGroup.id) {
      this.installationKIPiAGroupService()
        .update(this.installationKIPiAGroup)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.installationKIPiAGroup.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.installationKIPiAGroupService()
        .create(this.installationKIPiAGroup)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.installationKIPiAGroup.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveInstallationKIPiAGroup(installationKIPiAGroupId): void {
    this.installationKIPiAGroupService()
      .find(installationKIPiAGroupId)
      .then(res => {
        this.installationKIPiAGroup = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.installationKIPiAService()
      .retrieve()
      .then(res => {
        this.installationKIPiAS = res.data;
      });
    this.tenderService()
      .retrieve()
      .then(res => {
        this.tenders = res.data;
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

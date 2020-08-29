import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import InstallationKIPiAGroupService from '../installation-ki-pi-a-group/installation-ki-pi-a-group.service';
import { IInstallationKIPiAGroup } from '@/shared/model/installation-ki-pi-a-group.model';

import AlertService from '@/shared/alert/alert.service';
import { IInstallationKIPiA, InstallationKIPiA } from '@/shared/model/installation-ki-pi-a.model';
import InstallationKIPiAService from './installation-ki-pi-a.service';

const validations: any = {
  installationKIPiA: {
    title: {
      required,
      minLength: minLength(3),
    },
  },
};

@Component({
  validations,
})
export default class InstallationKIPiAUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('installationKIPiAService') private installationKIPiAService: () => InstallationKIPiAService;
  public installationKIPiA: IInstallationKIPiA = new InstallationKIPiA();

  @Inject('installationKIPiAGroupService') private installationKIPiAGroupService: () => InstallationKIPiAGroupService;

  public installationKIPiAGroups: IInstallationKIPiAGroup[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.installationKIPiAId) {
        vm.retrieveInstallationKIPiA(to.params.installationKIPiAId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.installationKIPiA.id) {
      this.installationKIPiAService()
        .update(this.installationKIPiA)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.installationKIPiA.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.installationKIPiAService()
        .create(this.installationKIPiA)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.installationKIPiA.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveInstallationKIPiA(installationKIPiAId): void {
    this.installationKIPiAService()
      .find(installationKIPiAId)
      .then(res => {
        this.installationKIPiA = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.installationKIPiAGroupService()
      .retrieve()
      .then(res => {
        this.installationKIPiAGroups = res.data;
      });
  }
}

import { Component, Vue, Inject } from 'vue-property-decorator';

import { IInstallationKIPiA } from '@/shared/model/installation-ki-pi-a.model';
import InstallationKIPiAService from './installation-ki-pi-a.service';

@Component
export default class InstallationKIPiADetails extends Vue {
  @Inject('installationKIPiAService') private installationKIPiAService: () => InstallationKIPiAService;
  public installationKIPiA: IInstallationKIPiA = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.installationKIPiAId) {
        vm.retrieveInstallationKIPiA(to.params.installationKIPiAId);
      }
    });
  }

  public retrieveInstallationKIPiA(installationKIPiAId) {
    this.installationKIPiAService()
      .find(installationKIPiAId)
      .then(res => {
        this.installationKIPiA = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

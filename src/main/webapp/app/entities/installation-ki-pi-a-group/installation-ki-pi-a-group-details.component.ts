import { Component, Vue, Inject } from 'vue-property-decorator';

import { IInstallationKIPiAGroup } from '@/shared/model/installation-ki-pi-a-group.model';
import InstallationKIPiAGroupService from './installation-ki-pi-a-group.service';

@Component
export default class InstallationKIPiAGroupDetails extends Vue {
  @Inject('installationKIPiAGroupService') private installationKIPiAGroupService: () => InstallationKIPiAGroupService;
  public installationKIPiAGroup: IInstallationKIPiAGroup = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.installationKIPiAGroupId) {
        vm.retrieveInstallationKIPiAGroup(to.params.installationKIPiAGroupId);
      }
    });
  }

  public retrieveInstallationKIPiAGroup(installationKIPiAGroupId) {
    this.installationKIPiAGroupService()
      .find(installationKIPiAGroupId)
      .then(res => {
        this.installationKIPiAGroup = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

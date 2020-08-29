import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUnitKIPiAGroup } from '@/shared/model/unit-ki-pi-a-group.model';
import UnitKIPiAGroupService from './unit-ki-pi-a-group.service';

@Component
export default class UnitKIPiAGroupDetails extends Vue {
  @Inject('unitKIPiAGroupService') private unitKIPiAGroupService: () => UnitKIPiAGroupService;
  public unitKIPiAGroup: IUnitKIPiAGroup = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.unitKIPiAGroupId) {
        vm.retrieveUnitKIPiAGroup(to.params.unitKIPiAGroupId);
      }
    });
  }

  public retrieveUnitKIPiAGroup(unitKIPiAGroupId) {
    this.unitKIPiAGroupService()
      .find(unitKIPiAGroupId)
      .then(res => {
        this.unitKIPiAGroup = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

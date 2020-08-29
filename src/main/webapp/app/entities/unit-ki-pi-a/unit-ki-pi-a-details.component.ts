import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUnitKIPiA } from '@/shared/model/unit-ki-pi-a.model';
import UnitKIPiAService from './unit-ki-pi-a.service';

@Component
export default class UnitKIPiADetails extends Vue {
  @Inject('unitKIPiAService') private unitKIPiAService: () => UnitKIPiAService;
  public unitKIPiA: IUnitKIPiA = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.unitKIPiAId) {
        vm.retrieveUnitKIPiA(to.params.unitKIPiAId);
      }
    });
  }

  public retrieveUnitKIPiA(unitKIPiAId) {
    this.unitKIPiAService()
      .find(unitKIPiAId)
      .then(res => {
        this.unitKIPiA = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

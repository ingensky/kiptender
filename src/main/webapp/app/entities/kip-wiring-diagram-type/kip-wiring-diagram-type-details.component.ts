import { Component, Vue, Inject } from 'vue-property-decorator';

import { IKIPWiringDiagramType } from '@/shared/model/kip-wiring-diagram-type.model';
import KIPWiringDiagramTypeService from './kip-wiring-diagram-type.service';

@Component
export default class KIPWiringDiagramTypeDetails extends Vue {
  @Inject('kIPWiringDiagramTypeService') private kIPWiringDiagramTypeService: () => KIPWiringDiagramTypeService;
  public kIPWiringDiagramType: IKIPWiringDiagramType = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.kIPWiringDiagramTypeId) {
        vm.retrieveKIPWiringDiagramType(to.params.kIPWiringDiagramTypeId);
      }
    });
  }

  public retrieveKIPWiringDiagramType(kIPWiringDiagramTypeId) {
    this.kIPWiringDiagramTypeService()
      .find(kIPWiringDiagramTypeId)
      .then(res => {
        this.kIPWiringDiagramType = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

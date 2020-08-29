import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITender } from '@/shared/model/tender.model';
import TenderService from './tender.service';

@Component
export default class TenderDetails extends Vue {
  @Inject('tenderService') private tenderService: () => TenderService;
  public tender: ITender = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tenderId) {
        vm.retrieveTender(to.params.tenderId);
      }
    });
  }

  public retrieveTender(tenderId) {
    this.tenderService()
      .find(tenderId)
      .then(res => {
        this.tender = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

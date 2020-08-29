import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import UnitKIPiAService from '../unit-ki-pi-a/unit-ki-pi-a.service';
import { IUnitKIPiA } from '@/shared/model/unit-ki-pi-a.model';

import TenderService from '../tender/tender.service';
import { ITender } from '@/shared/model/tender.model';

import AlertService from '@/shared/alert/alert.service';
import { IUnitKIPiAGroup, UnitKIPiAGroup } from '@/shared/model/unit-ki-pi-a-group.model';
import UnitKIPiAGroupService from './unit-ki-pi-a-group.service';

const validations: any = {
  unitKIPiAGroup: {
    quantity: {},
  },
};

@Component({
  validations,
})
export default class UnitKIPiAGroupUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('unitKIPiAGroupService') private unitKIPiAGroupService: () => UnitKIPiAGroupService;
  public unitKIPiAGroup: IUnitKIPiAGroup = new UnitKIPiAGroup();

  @Inject('unitKIPiAService') private unitKIPiAService: () => UnitKIPiAService;

  public unitKIPiAS: IUnitKIPiA[] = [];

  @Inject('tenderService') private tenderService: () => TenderService;

  public tenders: ITender[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.unitKIPiAGroupId) {
        vm.retrieveUnitKIPiAGroup(to.params.unitKIPiAGroupId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.unitKIPiAGroup.unitKIPiAS = [];
  }

  public save(): void {
    this.isSaving = true;
    if (this.unitKIPiAGroup.id) {
      this.unitKIPiAGroupService()
        .update(this.unitKIPiAGroup)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.unitKIPiAGroup.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.unitKIPiAGroupService()
        .create(this.unitKIPiAGroup)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.unitKIPiAGroup.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveUnitKIPiAGroup(unitKIPiAGroupId): void {
    this.unitKIPiAGroupService()
      .find(unitKIPiAGroupId)
      .then(res => {
        this.unitKIPiAGroup = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.unitKIPiAService()
      .retrieve()
      .then(res => {
        this.unitKIPiAS = res.data;
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

import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IKIPWiringDiagramType, KIPWiringDiagramType } from '@/shared/model/kip-wiring-diagram-type.model';
import KIPWiringDiagramTypeService from './kip-wiring-diagram-type.service';

const validations: any = {
  kIPWiringDiagramType: {
    title: {
      required,
      minLength: minLength(3),
    },
    description: {
      minLength: minLength(3),
    },
  },
};

@Component({
  validations,
})
export default class KIPWiringDiagramTypeUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('kIPWiringDiagramTypeService') private kIPWiringDiagramTypeService: () => KIPWiringDiagramTypeService;
  public kIPWiringDiagramType: IKIPWiringDiagramType = new KIPWiringDiagramType();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.kIPWiringDiagramTypeId) {
        vm.retrieveKIPWiringDiagramType(to.params.kIPWiringDiagramTypeId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.kIPWiringDiagramType.id) {
      this.kIPWiringDiagramTypeService()
        .update(this.kIPWiringDiagramType)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.kIPWiringDiagramType.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.kIPWiringDiagramTypeService()
        .create(this.kIPWiringDiagramType)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.kIPWiringDiagramType.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveKIPWiringDiagramType(kIPWiringDiagramTypeId): void {
    this.kIPWiringDiagramTypeService()
      .find(kIPWiringDiagramTypeId)
      .then(res => {
        this.kIPWiringDiagramType = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}

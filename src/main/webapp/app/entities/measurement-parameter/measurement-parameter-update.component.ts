import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IMeasurementParameter, MeasurementParameter } from '@/shared/model/measurement-parameter.model';
import MeasurementParameterService from './measurement-parameter.service';

const validations: any = {
  measurementParameter: {
    title: {},
  },
};

@Component({
  validations,
})
export default class MeasurementParameterUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('measurementParameterService') private measurementParameterService: () => MeasurementParameterService;
  public measurementParameter: IMeasurementParameter = new MeasurementParameter();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.measurementParameterId) {
        vm.retrieveMeasurementParameter(to.params.measurementParameterId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.measurementParameter.id) {
      this.measurementParameterService()
        .update(this.measurementParameter)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.measurementParameter.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.measurementParameterService()
        .create(this.measurementParameter)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.measurementParameter.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveMeasurementParameter(measurementParameterId): void {
    this.measurementParameterService()
      .find(measurementParameterId)
      .then(res => {
        this.measurementParameter = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}

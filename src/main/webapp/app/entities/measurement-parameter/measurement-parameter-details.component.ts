import { Component, Vue, Inject } from 'vue-property-decorator';

import { IMeasurementParameter } from '@/shared/model/measurement-parameter.model';
import MeasurementParameterService from './measurement-parameter.service';

@Component
export default class MeasurementParameterDetails extends Vue {
  @Inject('measurementParameterService') private measurementParameterService: () => MeasurementParameterService;
  public measurementParameter: IMeasurementParameter = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.measurementParameterId) {
        vm.retrieveMeasurementParameter(to.params.measurementParameterId);
      }
    });
  }

  public retrieveMeasurementParameter(measurementParameterId) {
    this.measurementParameterService()
      .find(measurementParameterId)
      .then(res => {
        this.measurementParameter = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

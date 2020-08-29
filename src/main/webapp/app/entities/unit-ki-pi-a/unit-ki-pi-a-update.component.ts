import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import KIPWiringDiagramTypeService from '../kip-wiring-diagram-type/kip-wiring-diagram-type.service';
import { IKIPWiringDiagramType } from '@/shared/model/kip-wiring-diagram-type.model';

import MeasurementParameterService from '../measurement-parameter/measurement-parameter.service';
import { IMeasurementParameter } from '@/shared/model/measurement-parameter.model';

import UnitKIPiAGroupService from '../unit-ki-pi-a-group/unit-ki-pi-a-group.service';
import { IUnitKIPiAGroup } from '@/shared/model/unit-ki-pi-a-group.model';

import AlertService from '@/shared/alert/alert.service';
import { IUnitKIPiA, UnitKIPiA } from '@/shared/model/unit-ki-pi-a.model';
import UnitKIPiAService from './unit-ki-pi-a.service';

const validations: any = {
  unitKIPiA: {
    title: {
      required,
      minLength: minLength(3),
    },
    description: {
      minLength: minLength(3),
    },
    systemRSUPAZ: {},
    rootPlanPresence: {},
    needProjectOrder: {},
    needProjectCalculation: {},
    needElectricalHeating: {},
    numberAI: {},
    numberAO: {},
    numberDI: {},
    numberDO: {},
    basicLaborInput: {},
  },
};

@Component({
  validations,
})
export default class UnitKIPiAUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('unitKIPiAService') private unitKIPiAService: () => UnitKIPiAService;
  public unitKIPiA: IUnitKIPiA = new UnitKIPiA();

  @Inject('kIPWiringDiagramTypeService') private kIPWiringDiagramTypeService: () => KIPWiringDiagramTypeService;

  public kIPWiringDiagramTypes: IKIPWiringDiagramType[] = [];

  @Inject('measurementParameterService') private measurementParameterService: () => MeasurementParameterService;

  public measurementParameters: IMeasurementParameter[] = [];

  @Inject('unitKIPiAGroupService') private unitKIPiAGroupService: () => UnitKIPiAGroupService;

  public unitKIPiAGroups: IUnitKIPiAGroup[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.unitKIPiAId) {
        vm.retrieveUnitKIPiA(to.params.unitKIPiAId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.unitKIPiA.id) {
      this.unitKIPiAService()
        .update(this.unitKIPiA)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.unitKIPiA.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.unitKIPiAService()
        .create(this.unitKIPiA)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.unitKIPiA.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveUnitKIPiA(unitKIPiAId): void {
    this.unitKIPiAService()
      .find(unitKIPiAId)
      .then(res => {
        this.unitKIPiA = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.kIPWiringDiagramTypeService()
      .retrieve()
      .then(res => {
        this.kIPWiringDiagramTypes = res.data;
      });
    this.measurementParameterService()
      .retrieve()
      .then(res => {
        this.measurementParameters = res.data;
      });
    this.unitKIPiAGroupService()
      .retrieve()
      .then(res => {
        this.unitKIPiAGroups = res.data;
      });
  }
}

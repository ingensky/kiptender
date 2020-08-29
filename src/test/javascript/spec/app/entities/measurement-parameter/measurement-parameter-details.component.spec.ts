/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import MeasurementParameterDetailComponent from '@/entities/measurement-parameter/measurement-parameter-details.vue';
import MeasurementParameterClass from '@/entities/measurement-parameter/measurement-parameter-details.component';
import MeasurementParameterService from '@/entities/measurement-parameter/measurement-parameter.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('MeasurementParameter Management Detail Component', () => {
    let wrapper: Wrapper<MeasurementParameterClass>;
    let comp: MeasurementParameterClass;
    let measurementParameterServiceStub: SinonStubbedInstance<MeasurementParameterService>;

    beforeEach(() => {
      measurementParameterServiceStub = sinon.createStubInstance<MeasurementParameterService>(MeasurementParameterService);

      wrapper = shallowMount<MeasurementParameterClass>(MeasurementParameterDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { measurementParameterService: () => measurementParameterServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundMeasurementParameter = { id: 123 };
        measurementParameterServiceStub.find.resolves(foundMeasurementParameter);

        // WHEN
        comp.retrieveMeasurementParameter(123);
        await comp.$nextTick();

        // THEN
        expect(comp.measurementParameter).toBe(foundMeasurementParameter);
      });
    });
  });
});

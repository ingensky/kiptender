/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import MeasurementParameterUpdateComponent from '@/entities/measurement-parameter/measurement-parameter-update.vue';
import MeasurementParameterClass from '@/entities/measurement-parameter/measurement-parameter-update.component';
import MeasurementParameterService from '@/entities/measurement-parameter/measurement-parameter.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('MeasurementParameter Management Update Component', () => {
    let wrapper: Wrapper<MeasurementParameterClass>;
    let comp: MeasurementParameterClass;
    let measurementParameterServiceStub: SinonStubbedInstance<MeasurementParameterService>;

    beforeEach(() => {
      measurementParameterServiceStub = sinon.createStubInstance<MeasurementParameterService>(MeasurementParameterService);

      wrapper = shallowMount<MeasurementParameterClass>(MeasurementParameterUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          measurementParameterService: () => measurementParameterServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.measurementParameter = entity;
        measurementParameterServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(measurementParameterServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.measurementParameter = entity;
        measurementParameterServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(measurementParameterServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

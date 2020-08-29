/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import UnitKIPiAUpdateComponent from '@/entities/unit-ki-pi-a/unit-ki-pi-a-update.vue';
import UnitKIPiAClass from '@/entities/unit-ki-pi-a/unit-ki-pi-a-update.component';
import UnitKIPiAService from '@/entities/unit-ki-pi-a/unit-ki-pi-a.service';

import KIPWiringDiagramTypeService from '@/entities/kip-wiring-diagram-type/kip-wiring-diagram-type.service';

import MeasurementParameterService from '@/entities/measurement-parameter/measurement-parameter.service';

import UnitKIPiAGroupService from '@/entities/unit-ki-pi-a-group/unit-ki-pi-a-group.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('UnitKIPiA Management Update Component', () => {
    let wrapper: Wrapper<UnitKIPiAClass>;
    let comp: UnitKIPiAClass;
    let unitKIPiAServiceStub: SinonStubbedInstance<UnitKIPiAService>;

    beforeEach(() => {
      unitKIPiAServiceStub = sinon.createStubInstance<UnitKIPiAService>(UnitKIPiAService);

      wrapper = shallowMount<UnitKIPiAClass>(UnitKIPiAUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          unitKIPiAService: () => unitKIPiAServiceStub,

          kIPWiringDiagramTypeService: () => new KIPWiringDiagramTypeService(),

          measurementParameterService: () => new MeasurementParameterService(),

          unitKIPiAGroupService: () => new UnitKIPiAGroupService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.unitKIPiA = entity;
        unitKIPiAServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(unitKIPiAServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.unitKIPiA = entity;
        unitKIPiAServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(unitKIPiAServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

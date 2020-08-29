/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import UnitKIPiAGroupUpdateComponent from '@/entities/unit-ki-pi-a-group/unit-ki-pi-a-group-update.vue';
import UnitKIPiAGroupClass from '@/entities/unit-ki-pi-a-group/unit-ki-pi-a-group-update.component';
import UnitKIPiAGroupService from '@/entities/unit-ki-pi-a-group/unit-ki-pi-a-group.service';

import UnitKIPiAService from '@/entities/unit-ki-pi-a/unit-ki-pi-a.service';

import TenderService from '@/entities/tender/tender.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('UnitKIPiAGroup Management Update Component', () => {
    let wrapper: Wrapper<UnitKIPiAGroupClass>;
    let comp: UnitKIPiAGroupClass;
    let unitKIPiAGroupServiceStub: SinonStubbedInstance<UnitKIPiAGroupService>;

    beforeEach(() => {
      unitKIPiAGroupServiceStub = sinon.createStubInstance<UnitKIPiAGroupService>(UnitKIPiAGroupService);

      wrapper = shallowMount<UnitKIPiAGroupClass>(UnitKIPiAGroupUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          unitKIPiAGroupService: () => unitKIPiAGroupServiceStub,

          unitKIPiAService: () => new UnitKIPiAService(),

          tenderService: () => new TenderService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.unitKIPiAGroup = entity;
        unitKIPiAGroupServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(unitKIPiAGroupServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.unitKIPiAGroup = entity;
        unitKIPiAGroupServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(unitKIPiAGroupServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

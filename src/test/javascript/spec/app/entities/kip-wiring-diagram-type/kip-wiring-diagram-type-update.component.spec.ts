/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import KIPWiringDiagramTypeUpdateComponent from '@/entities/kip-wiring-diagram-type/kip-wiring-diagram-type-update.vue';
import KIPWiringDiagramTypeClass from '@/entities/kip-wiring-diagram-type/kip-wiring-diagram-type-update.component';
import KIPWiringDiagramTypeService from '@/entities/kip-wiring-diagram-type/kip-wiring-diagram-type.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('KIPWiringDiagramType Management Update Component', () => {
    let wrapper: Wrapper<KIPWiringDiagramTypeClass>;
    let comp: KIPWiringDiagramTypeClass;
    let kIPWiringDiagramTypeServiceStub: SinonStubbedInstance<KIPWiringDiagramTypeService>;

    beforeEach(() => {
      kIPWiringDiagramTypeServiceStub = sinon.createStubInstance<KIPWiringDiagramTypeService>(KIPWiringDiagramTypeService);

      wrapper = shallowMount<KIPWiringDiagramTypeClass>(KIPWiringDiagramTypeUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          kIPWiringDiagramTypeService: () => kIPWiringDiagramTypeServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.kIPWiringDiagramType = entity;
        kIPWiringDiagramTypeServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(kIPWiringDiagramTypeServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.kIPWiringDiagramType = entity;
        kIPWiringDiagramTypeServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(kIPWiringDiagramTypeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

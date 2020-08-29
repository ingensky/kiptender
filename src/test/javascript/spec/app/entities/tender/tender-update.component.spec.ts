/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import TenderUpdateComponent from '@/entities/tender/tender-update.vue';
import TenderClass from '@/entities/tender/tender-update.component';
import TenderService from '@/entities/tender/tender.service';

import ProjectMarkService from '@/entities/project-mark/project-mark.service';

import UnitKIPiAGroupService from '@/entities/unit-ki-pi-a-group/unit-ki-pi-a-group.service';

import InstallationKIPiAGroupService from '@/entities/installation-ki-pi-a-group/installation-ki-pi-a-group.service';

import ClientService from '@/entities/client/client.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Tender Management Update Component', () => {
    let wrapper: Wrapper<TenderClass>;
    let comp: TenderClass;
    let tenderServiceStub: SinonStubbedInstance<TenderService>;

    beforeEach(() => {
      tenderServiceStub = sinon.createStubInstance<TenderService>(TenderService);

      wrapper = shallowMount<TenderClass>(TenderUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          tenderService: () => tenderServiceStub,

          projectMarkService: () => new ProjectMarkService(),

          unitKIPiAGroupService: () => new UnitKIPiAGroupService(),

          installationKIPiAGroupService: () => new InstallationKIPiAGroupService(),

          clientService: () => new ClientService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.tender = entity;
        tenderServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tenderServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.tender = entity;
        tenderServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tenderServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

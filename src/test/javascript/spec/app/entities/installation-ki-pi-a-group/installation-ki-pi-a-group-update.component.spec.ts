/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import InstallationKIPiAGroupUpdateComponent from '@/entities/installation-ki-pi-a-group/installation-ki-pi-a-group-update.vue';
import InstallationKIPiAGroupClass from '@/entities/installation-ki-pi-a-group/installation-ki-pi-a-group-update.component';
import InstallationKIPiAGroupService from '@/entities/installation-ki-pi-a-group/installation-ki-pi-a-group.service';

import InstallationKIPiAService from '@/entities/installation-ki-pi-a/installation-ki-pi-a.service';

import TenderService from '@/entities/tender/tender.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('InstallationKIPiAGroup Management Update Component', () => {
    let wrapper: Wrapper<InstallationKIPiAGroupClass>;
    let comp: InstallationKIPiAGroupClass;
    let installationKIPiAGroupServiceStub: SinonStubbedInstance<InstallationKIPiAGroupService>;

    beforeEach(() => {
      installationKIPiAGroupServiceStub = sinon.createStubInstance<InstallationKIPiAGroupService>(InstallationKIPiAGroupService);

      wrapper = shallowMount<InstallationKIPiAGroupClass>(InstallationKIPiAGroupUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          installationKIPiAGroupService: () => installationKIPiAGroupServiceStub,

          installationKIPiAService: () => new InstallationKIPiAService(),

          tenderService: () => new TenderService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.installationKIPiAGroup = entity;
        installationKIPiAGroupServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(installationKIPiAGroupServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.installationKIPiAGroup = entity;
        installationKIPiAGroupServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(installationKIPiAGroupServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

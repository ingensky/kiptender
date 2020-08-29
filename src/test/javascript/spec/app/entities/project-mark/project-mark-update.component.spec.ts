/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ProjectMarkUpdateComponent from '@/entities/project-mark/project-mark-update.vue';
import ProjectMarkClass from '@/entities/project-mark/project-mark-update.component';
import ProjectMarkService from '@/entities/project-mark/project-mark.service';

import TenderService from '@/entities/tender/tender.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('ProjectMark Management Update Component', () => {
    let wrapper: Wrapper<ProjectMarkClass>;
    let comp: ProjectMarkClass;
    let projectMarkServiceStub: SinonStubbedInstance<ProjectMarkService>;

    beforeEach(() => {
      projectMarkServiceStub = sinon.createStubInstance<ProjectMarkService>(ProjectMarkService);

      wrapper = shallowMount<ProjectMarkClass>(ProjectMarkUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          projectMarkService: () => projectMarkServiceStub,

          tenderService: () => new TenderService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.projectMark = entity;
        projectMarkServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectMarkServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.projectMark = entity;
        projectMarkServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectMarkServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

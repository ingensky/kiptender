/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ProjectMarkDetailComponent from '@/entities/project-mark/project-mark-details.vue';
import ProjectMarkClass from '@/entities/project-mark/project-mark-details.component';
import ProjectMarkService from '@/entities/project-mark/project-mark.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ProjectMark Management Detail Component', () => {
    let wrapper: Wrapper<ProjectMarkClass>;
    let comp: ProjectMarkClass;
    let projectMarkServiceStub: SinonStubbedInstance<ProjectMarkService>;

    beforeEach(() => {
      projectMarkServiceStub = sinon.createStubInstance<ProjectMarkService>(ProjectMarkService);

      wrapper = shallowMount<ProjectMarkClass>(ProjectMarkDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { projectMarkService: () => projectMarkServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundProjectMark = { id: 123 };
        projectMarkServiceStub.find.resolves(foundProjectMark);

        // WHEN
        comp.retrieveProjectMark(123);
        await comp.$nextTick();

        // THEN
        expect(comp.projectMark).toBe(foundProjectMark);
      });
    });
  });
});

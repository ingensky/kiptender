/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import TenderDetailComponent from '@/entities/tender/tender-details.vue';
import TenderClass from '@/entities/tender/tender-details.component';
import TenderService from '@/entities/tender/tender.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Tender Management Detail Component', () => {
    let wrapper: Wrapper<TenderClass>;
    let comp: TenderClass;
    let tenderServiceStub: SinonStubbedInstance<TenderService>;

    beforeEach(() => {
      tenderServiceStub = sinon.createStubInstance<TenderService>(TenderService);

      wrapper = shallowMount<TenderClass>(TenderDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { tenderService: () => tenderServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTender = { id: 123 };
        tenderServiceStub.find.resolves(foundTender);

        // WHEN
        comp.retrieveTender(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tender).toBe(foundTender);
      });
    });
  });
});

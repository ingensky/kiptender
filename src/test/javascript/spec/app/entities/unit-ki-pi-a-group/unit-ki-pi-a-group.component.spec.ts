/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import UnitKIPiAGroupComponent from '@/entities/unit-ki-pi-a-group/unit-ki-pi-a-group.vue';
import UnitKIPiAGroupClass from '@/entities/unit-ki-pi-a-group/unit-ki-pi-a-group.component';
import UnitKIPiAGroupService from '@/entities/unit-ki-pi-a-group/unit-ki-pi-a-group.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.component('jhi-sort-indicator', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('UnitKIPiAGroup Management Component', () => {
    let wrapper: Wrapper<UnitKIPiAGroupClass>;
    let comp: UnitKIPiAGroupClass;
    let unitKIPiAGroupServiceStub: SinonStubbedInstance<UnitKIPiAGroupService>;

    beforeEach(() => {
      unitKIPiAGroupServiceStub = sinon.createStubInstance<UnitKIPiAGroupService>(UnitKIPiAGroupService);
      unitKIPiAGroupServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<UnitKIPiAGroupClass>(UnitKIPiAGroupComponent, {
        store,
        i18n,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          unitKIPiAGroupService: () => unitKIPiAGroupServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      unitKIPiAGroupServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllUnitKIPiAGroups();
      await comp.$nextTick();

      // THEN
      expect(unitKIPiAGroupServiceStub.retrieve.called).toBeTruthy();
      expect(comp.unitKIPiAGroups[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      unitKIPiAGroupServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(unitKIPiAGroupServiceStub.retrieve.called).toBeTruthy();
      expect(comp.unitKIPiAGroups[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should not load a page if the page is the same as the previous page', () => {
      // GIVEN
      unitKIPiAGroupServiceStub.retrieve.reset();
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(unitKIPiAGroupServiceStub.retrieve.called).toBeFalsy();
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      unitKIPiAGroupServiceStub.retrieve.reset();
      unitKIPiAGroupServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(unitKIPiAGroupServiceStub.retrieve.callCount).toEqual(3);
      expect(comp.page).toEqual(1);
      expect(comp.unitKIPiAGroups[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.propOrder = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      unitKIPiAGroupServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeUnitKIPiAGroup();
      await comp.$nextTick();

      // THEN
      expect(unitKIPiAGroupServiceStub.delete.called).toBeTruthy();
      expect(unitKIPiAGroupServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});

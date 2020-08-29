import Vue from 'vue';
import Component from 'vue-class-component';
Component.registerHooks([
  'beforeRouteEnter',
  'beforeRouteLeave',
  'beforeRouteUpdate', // for vue-router 2.2+
]);
import Router from 'vue-router';
import { Authority } from '@/shared/security/authority';
const Home = () => import('../core/home/home.vue');
const Error = () => import('../core/error/error.vue');
const Register = () => import('../account/register/register.vue');
const Activate = () => import('../account/activate/activate.vue');
const ResetPasswordInit = () => import('../account/reset-password/init/reset-password-init.vue');
const ResetPasswordFinish = () => import('../account/reset-password/finish/reset-password-finish.vue');
const ChangePassword = () => import('../account/change-password/change-password.vue');
const Settings = () => import('../account/settings/settings.vue');
const JhiUserManagementComponent = () => import('../admin/user-management/user-management.vue');
const JhiUserManagementViewComponent = () => import('../admin/user-management/user-management-view.vue');
const JhiUserManagementEditComponent = () => import('../admin/user-management/user-management-edit.vue');
const Sessions = () => import('../account/sessions/sessions.vue');
const JhiConfigurationComponent = () => import('../admin/configuration/configuration.vue');
const JhiDocsComponent = () => import('../admin/docs/docs.vue');
const JhiHealthComponent = () => import('../admin/health/health.vue');
const JhiLogsComponent = () => import('../admin/logs/logs.vue');
const JhiAuditsComponent = () => import('../admin/audits/audits.vue');
const JhiMetricsComponent = () => import('../admin/metrics/metrics.vue');
/* tslint:disable */
// prettier-ignore
const MeasurementParameter = () => import('../entities/measurement-parameter/measurement-parameter.vue');
// prettier-ignore
const MeasurementParameterUpdate = () => import('../entities/measurement-parameter/measurement-parameter-update.vue');
// prettier-ignore
const MeasurementParameterDetails = () => import('../entities/measurement-parameter/measurement-parameter-details.vue');
// prettier-ignore
const UnitKIPiA = () => import('../entities/unit-ki-pi-a/unit-ki-pi-a.vue');
// prettier-ignore
const UnitKIPiAUpdate = () => import('../entities/unit-ki-pi-a/unit-ki-pi-a-update.vue');
// prettier-ignore
const UnitKIPiADetails = () => import('../entities/unit-ki-pi-a/unit-ki-pi-a-details.vue');
// prettier-ignore
const InstallationKIPiA = () => import('../entities/installation-ki-pi-a/installation-ki-pi-a.vue');
// prettier-ignore
const InstallationKIPiAUpdate = () => import('../entities/installation-ki-pi-a/installation-ki-pi-a-update.vue');
// prettier-ignore
const InstallationKIPiADetails = () => import('../entities/installation-ki-pi-a/installation-ki-pi-a-details.vue');
// prettier-ignore
const Tender = () => import('../entities/tender/tender.vue');
// prettier-ignore
const TenderUpdate = () => import('../entities/tender/tender-update.vue');
// prettier-ignore
const TenderDetails = () => import('../entities/tender/tender-details.vue');
// prettier-ignore
const ProjectMark = () => import('../entities/project-mark/project-mark.vue');
// prettier-ignore
const ProjectMarkUpdate = () => import('../entities/project-mark/project-mark-update.vue');
// prettier-ignore
const ProjectMarkDetails = () => import('../entities/project-mark/project-mark-details.vue');
// prettier-ignore
const UnitKIPiAGroup = () => import('../entities/unit-ki-pi-a-group/unit-ki-pi-a-group.vue');
// prettier-ignore
const UnitKIPiAGroupUpdate = () => import('../entities/unit-ki-pi-a-group/unit-ki-pi-a-group-update.vue');
// prettier-ignore
const UnitKIPiAGroupDetails = () => import('../entities/unit-ki-pi-a-group/unit-ki-pi-a-group-details.vue');
// prettier-ignore
const InstallationKIPiAGroup = () => import('../entities/installation-ki-pi-a-group/installation-ki-pi-a-group.vue');
// prettier-ignore
const InstallationKIPiAGroupUpdate = () => import('../entities/installation-ki-pi-a-group/installation-ki-pi-a-group-update.vue');
// prettier-ignore
const InstallationKIPiAGroupDetails = () => import('../entities/installation-ki-pi-a-group/installation-ki-pi-a-group-details.vue');
// prettier-ignore
const KIPWiringDiagramType = () => import('../entities/kip-wiring-diagram-type/kip-wiring-diagram-type.vue');
// prettier-ignore
const KIPWiringDiagramTypeUpdate = () => import('../entities/kip-wiring-diagram-type/kip-wiring-diagram-type-update.vue');
// prettier-ignore
const KIPWiringDiagramTypeDetails = () => import('../entities/kip-wiring-diagram-type/kip-wiring-diagram-type-details.vue');
// prettier-ignore
const Client = () => import('../entities/client/client.vue');
// prettier-ignore
const ClientUpdate = () => import('../entities/client/client-update.vue');
// prettier-ignore
const ClientDetails = () => import('../entities/client/client-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

Vue.use(Router);

// prettier-ignore
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/forbidden',
      name: 'Forbidden',
      component: Error,
      meta: { error403: true }
    },
    {
      path: '/not-found',
      name: 'NotFound',
      component: Error,
      meta: { error404: true }
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/account/activate',
      name: 'Activate',
      component: Activate
    },
    {
      path: '/account/reset/request',
      name: 'ResetPasswordInit',
      component: ResetPasswordInit
    },
    {
      path: '/account/reset/finish',
      name: 'ResetPasswordFinish',
      component: ResetPasswordFinish
    },
    {
      path: '/account/password',
      name: 'ChangePassword',
      component: ChangePassword,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/account/sessions',
      name: 'Sessions',
      component: Sessions,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/account/settings',
      name: 'Settings',
      component: Settings,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/admin/user-management',
      name: 'JhiUser',
      component: JhiUserManagementComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/user-management/new',
      name: 'JhiUserCreate',
      component: JhiUserManagementEditComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/user-management/:userId/edit',
      name: 'JhiUserEdit',
      component: JhiUserManagementEditComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/user-management/:userId/view',
      name: 'JhiUserView',
      component: JhiUserManagementViewComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/docs',
      name: 'JhiDocsComponent',
      component: JhiDocsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/audits',
      name: 'JhiAuditsComponent',
      component: JhiAuditsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/jhi-health',
      name: 'JhiHealthComponent',
      component: JhiHealthComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/logs',
      name: 'JhiLogsComponent',
      component: JhiLogsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/jhi-metrics',
      name: 'JhiMetricsComponent',
      component: JhiMetricsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/jhi-configuration',
      name: 'JhiConfigurationComponent',
      component: JhiConfigurationComponent,
      meta: { authorities: [Authority.ADMIN] }
    }
    ,
    {
      path: '/measurement-parameter',
      name: 'MeasurementParameter',
      component: MeasurementParameter,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/measurement-parameter/new',
      name: 'MeasurementParameterCreate',
      component: MeasurementParameterUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/measurement-parameter/:measurementParameterId/edit',
      name: 'MeasurementParameterEdit',
      component: MeasurementParameterUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/measurement-parameter/:measurementParameterId/view',
      name: 'MeasurementParameterView',
      component: MeasurementParameterDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/unit-ki-pi-a',
      name: 'UnitKIPiA',
      component: UnitKIPiA,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/unit-ki-pi-a/new',
      name: 'UnitKIPiACreate',
      component: UnitKIPiAUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/unit-ki-pi-a/:unitKIPiAId/edit',
      name: 'UnitKIPiAEdit',
      component: UnitKIPiAUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/unit-ki-pi-a/:unitKIPiAId/view',
      name: 'UnitKIPiAView',
      component: UnitKIPiADetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/installation-ki-pi-a',
      name: 'InstallationKIPiA',
      component: InstallationKIPiA,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/installation-ki-pi-a/new',
      name: 'InstallationKIPiACreate',
      component: InstallationKIPiAUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/installation-ki-pi-a/:installationKIPiAId/edit',
      name: 'InstallationKIPiAEdit',
      component: InstallationKIPiAUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/installation-ki-pi-a/:installationKIPiAId/view',
      name: 'InstallationKIPiAView',
      component: InstallationKIPiADetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/tender',
      name: 'Tender',
      component: Tender,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/tender/new',
      name: 'TenderCreate',
      component: TenderUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/tender/:tenderId/edit',
      name: 'TenderEdit',
      component: TenderUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/tender/:tenderId/view',
      name: 'TenderView',
      component: TenderDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/project-mark',
      name: 'ProjectMark',
      component: ProjectMark,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/project-mark/new',
      name: 'ProjectMarkCreate',
      component: ProjectMarkUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/project-mark/:projectMarkId/edit',
      name: 'ProjectMarkEdit',
      component: ProjectMarkUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/project-mark/:projectMarkId/view',
      name: 'ProjectMarkView',
      component: ProjectMarkDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/unit-ki-pi-a-group',
      name: 'UnitKIPiAGroup',
      component: UnitKIPiAGroup,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/unit-ki-pi-a-group/new',
      name: 'UnitKIPiAGroupCreate',
      component: UnitKIPiAGroupUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/unit-ki-pi-a-group/:unitKIPiAGroupId/edit',
      name: 'UnitKIPiAGroupEdit',
      component: UnitKIPiAGroupUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/unit-ki-pi-a-group/:unitKIPiAGroupId/view',
      name: 'UnitKIPiAGroupView',
      component: UnitKIPiAGroupDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/installation-ki-pi-a-group',
      name: 'InstallationKIPiAGroup',
      component: InstallationKIPiAGroup,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/installation-ki-pi-a-group/new',
      name: 'InstallationKIPiAGroupCreate',
      component: InstallationKIPiAGroupUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/installation-ki-pi-a-group/:installationKIPiAGroupId/edit',
      name: 'InstallationKIPiAGroupEdit',
      component: InstallationKIPiAGroupUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/installation-ki-pi-a-group/:installationKIPiAGroupId/view',
      name: 'InstallationKIPiAGroupView',
      component: InstallationKIPiAGroupDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/kip-wiring-diagram-type',
      name: 'KIPWiringDiagramType',
      component: KIPWiringDiagramType,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/kip-wiring-diagram-type/new',
      name: 'KIPWiringDiagramTypeCreate',
      component: KIPWiringDiagramTypeUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/kip-wiring-diagram-type/:kIPWiringDiagramTypeId/edit',
      name: 'KIPWiringDiagramTypeEdit',
      component: KIPWiringDiagramTypeUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/kip-wiring-diagram-type/:kIPWiringDiagramTypeId/view',
      name: 'KIPWiringDiagramTypeView',
      component: KIPWiringDiagramTypeDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/client',
      name: 'Client',
      component: Client,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/client/new',
      name: 'ClientCreate',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/client/:clientId/edit',
      name: 'ClientEdit',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/client/:clientId/view',
      name: 'ClientView',
      component: ClientDetails,
      meta: { authorities: [Authority.USER] }
    }
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ]
});

import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import TenderService from '../tender/tender.service';
import { ITender } from '@/shared/model/tender.model';

import AlertService from '@/shared/alert/alert.service';
import { IProjectMark, ProjectMark } from '@/shared/model/project-mark.model';
import ProjectMarkService from './project-mark.service';

const validations: any = {
  projectMark: {
    title: {
      required,
      minLength: minLength(3),
    },
    mark: {
      required,
      minLength: minLength(2),
    },
  },
};

@Component({
  validations,
})
export default class ProjectMarkUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('projectMarkService') private projectMarkService: () => ProjectMarkService;
  public projectMark: IProjectMark = new ProjectMark();

  @Inject('tenderService') private tenderService: () => TenderService;

  public tenders: ITender[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.projectMarkId) {
        vm.retrieveProjectMark(to.params.projectMarkId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.projectMark.id) {
      this.projectMarkService()
        .update(this.projectMark)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.projectMark.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.projectMarkService()
        .create(this.projectMark)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kiptenderApp.projectMark.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveProjectMark(projectMarkId): void {
    this.projectMarkService()
      .find(projectMarkId)
      .then(res => {
        this.projectMark = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.tenderService()
      .retrieve()
      .then(res => {
        this.tenders = res.data;
      });
  }
}

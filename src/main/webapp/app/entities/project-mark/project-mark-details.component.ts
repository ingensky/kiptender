import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProjectMark } from '@/shared/model/project-mark.model';
import ProjectMarkService from './project-mark.service';

@Component
export default class ProjectMarkDetails extends Vue {
  @Inject('projectMarkService') private projectMarkService: () => ProjectMarkService;
  public projectMark: IProjectMark = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.projectMarkId) {
        vm.retrieveProjectMark(to.params.projectMarkId);
      }
    });
  }

  public retrieveProjectMark(projectMarkId) {
    this.projectMarkService()
      .find(projectMarkId)
      .then(res => {
        this.projectMark = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="projectMark">
                <h2 class="jh-entity-heading"><span v-text="$t('kiptenderApp.projectMark.detail.title')">ProjectMark</span> {{projectMark.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kiptenderApp.projectMark.title')">Title</span>
                    </dt>
                    <dd>
                        <span>{{projectMark.title}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.projectMark.mark')">Mark</span>
                    </dt>
                    <dd>
                        <span>{{projectMark.mark}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="projectMark.id" :to="{name: 'ProjectMarkEdit', params: {projectMarkId: projectMark.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./project-mark-details.component.ts">
</script>

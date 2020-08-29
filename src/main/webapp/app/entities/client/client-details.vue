<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="client">
                <h2 class="jh-entity-heading"><span v-text="$t('kiptenderApp.client.detail.title')">Client</span> {{client.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kiptenderApp.client.title')">Title</span>
                    </dt>
                    <dd>
                        <span>{{client.title}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.client.contactName')">Contact Name</span>
                    </dt>
                    <dd>
                        <span>{{client.contactName}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.client.contactNumber')">Contact Number</span>
                    </dt>
                    <dd>
                        <span>{{client.contactNumber}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.client.description')">Description</span>
                    </dt>
                    <dd>
                        <span>{{client.description}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.client.user')">User</span>
                    </dt>
                    <dd>
                        {{client.user ? client.user.id : ''}}
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="client.id" :to="{name: 'ClientEdit', params: {clientId: client.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./client-details.component.ts">
</script>

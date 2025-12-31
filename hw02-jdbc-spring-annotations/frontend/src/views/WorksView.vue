<script setup>
  import { onMounted, ref} from 'vue'
  import { worksApi } from "../api/WorksApi.js";

  const items = ref([])
  const error = ref('')
  const loading = ref(false)

  onMounted(async () =>{
    loading.value = true;
    error.value = '';
    try{
      items.value = await worksApi.list();
    } catch (e) {
      error.value = e.message || String(e)
    } finally {
      loading.value = false
    }
  })
</script>


<template>
  <div class="container py-4">
    <h3 class="mb-3">Works</h3>

    <div v-if="error" class="alert alert-danger">{{ error }}</div>
    <div v-else-if="loading">Loading...</div>

    <table v-else class="table table-striped">
      <thead>
      <tr>
        <th style="width: 80px;">ID</th>
        <th>Title</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="w in items" :key="w.id">
        <td>{{ w.id }}</td>
        <td>{{ w.title }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

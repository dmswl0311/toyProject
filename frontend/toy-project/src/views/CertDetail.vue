<template>
    <div>
        <!-- 자격증 상세-->
        <h1>자격증 상세</h1>
        {{item}}
    </div>
</template>

<script>

export default {
  name: 'CertDetail',
  components: {
  },
  data () {
    return {
      item:this.$route.params.id,
    }
  },
  methods: {
    
  }
}


</script>



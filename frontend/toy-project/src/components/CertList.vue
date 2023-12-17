<template>
    <div>
        <!-- 자격증 리스트-->
        <div id="certList">
            <div  v-for="content in contents" v-bind:key="content.jmcd">
                <p>{{ content.seriesnm }}</p>
                    <router-link v-bind:to="`/cert/${content.jmcd}`">{{ content.jmfldnm }}</router-link>
                <hr />
            </div>
        </div> 
    </div>
</template>

<script>
// import CertDetail from '@/components/CertDetail.vue';
 
const HOST = "http://localhost:8082";
var pageNo = 10;

export default {
  name: 'CertList',
  components: {
    // CertDetail
  },
  data () {
    return {
      contents: '',
    }
  },
  created () {
    console.log("created");
    this.getCertAll();
  },
  methods: {
    getCertAll() {
        this.$axios
        .get(HOST+"/cert/all/"+pageNo)     
        .then((res) => {
            this.contents = res.data;
            console.log(this.contents)
        })
        .catch((err) => {
            console.log(err);
        })
    },
  }
}


</script>



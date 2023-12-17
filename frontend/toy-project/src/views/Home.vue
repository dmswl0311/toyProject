<template>
    <div>
        <h1>메인화면</h1>
        <p>{{ msg }}</p>
        <button @click="getData()">이벤트 함수 호출</button>
        <p>{{ contents }}</p>
        <CertList> </CertList>
        
    </div>
</template>

<script>
import CertList from '@/components/CertList.vue';

const HOST = "http://localhost:8082";
var contents;

export default {
  name: 'MainPage',
  components: {
    CertList
  },
  data () {
    return {
      msg: 'Vue.js 시작하기',
      contents: this.contents,
    }
  },
  methods: {
    getData() {
        this.$axios
        .get(HOST+"/open-api/cert-info?jmcd=0780")     
        .then((res) => {
            // console.log(res);
            this.contents = res.data[0].contents;
            console.log(contents);
        })
        .catch((err) => {
            console.log(err);
        })
        .finally(() => {
            console.log("finally");
        })
    },
  }
}


</script>

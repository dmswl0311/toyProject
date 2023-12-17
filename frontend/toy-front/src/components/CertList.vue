<template>
  <div>
    <div class="certList">
      <b-col md="6">
        <div v-for="content in contents" v-bind:key="content.jmcd">
          <b-card-group deck>
            <router-link v-bind:to="`/cert/${content.jmcd}`">
              <b-card
                :title="content.jmfldnm"
                tag="article"
                style="max-width: 20rem; min-height: 10rem"
                class="mb-2"
              >
                <b-card-text>{{ content.seriesnm }}</b-card-text>
              </b-card>
            </router-link>
          </b-card-group>
        </div>
      </b-col>
    </div>
  </div>
</template>

<script>
import { local } from "@/global/api";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
var pageNo = 10;

export default {
  name: "CertList",
  components: {},
  data() {
    return {
      contents: "",
    };
  },
  created() {
    console.log("created");
    this.getCertAll();
  },
  methods: {
    getCertAll() {
      this.$axios
        .get(local + "/cert/all/" + pageNo)
        .then((res) => {
          this.contents = res.data;
          console.log(this.contents);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
.certList {
  height: 100%;
}
</style>

<template>
  <div class="home">
    <!-- <div class="navi">navi</div> -->
    <div class="search">
      <div class="searchBar">
        <input
          id="searchQueryInput"
          type="text"
          name="searchQueryInput"
          placeholder="Search"
          value=""
        />
        <button id="searchQuerySubmit" type="submit" name="searchQuerySubmit">
          <svg style="width: 24px; height: 24px" viewBox="0 0 24 24">
            <path
              fill="#666666"
              d="M9.5,3A6.5,6.5 0 0,1 16,9.5C16,11.11 15.41,12.59 14.44,13.73L14.71,14H15.5L20.5,19L19,20.5L14,15.5V14.71L13.73,14.44C12.59,15.41 11.11,16 9.5,16A6.5,6.5 0 0,1 3,9.5A6.5,6.5 0 0,1 9.5,3M9.5,5C7,5 5,7 5,9.5C5,12 7,14 9.5,14C12,14 14,12 14,9.5C14,7 12,5 9.5,5Z"
            />
          </svg>
        </button>
      </div>
    </div>
    <div class="main">
      <h4>많은 사람이 보고 있어요!👀</h4>
      <CertList> </CertList>
    </div>
    <div class="footer">footer</div>
  </div>
</template>

<script>
import CertList from "@/components/CertList.vue";

export default {
  name: "HomeView",
  components: {
    CertList,
  },
  data() {
    return {};
  },
  methods: {},
  created() {},
};
</script>

<style scoped>
.home {
  /* width: 100vw; */
  height: 100%;
}
/* .navi {
  height: 10%;
} */
.search {
  margin-top: 1rem;
  height: 10%;
  margin-left: 1rem;
  margin-right: 1rem;
}
.main {
  height: 70%;
  background: gray;
}
.footer {
  height: 10%;
}
.searchBar {
  margin: 0 auto;
  text-align: center;
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
}

#searchQueryInput {
  width: 100%;
  height: 2.8rem;
  background: #f5f5f5;
  outline: none;
  border: none;
  border-radius: 1.625rem;
  padding: 0 3.5rem 0 1.5rem;
  font-size: 1rem;
}

#searchQuerySubmit {
  width: 3.5rem;
  height: 2.8rem;
  margin-left: -3.5rem;
  background: none;
  border: none;
  outline: none;
}

#searchQuerySubmit:hover {
  cursor: pointer;
}
</style>

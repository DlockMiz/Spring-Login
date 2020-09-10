import store from '~/store'

export default async (to, from, next) => {
	var appVersion = JSON.parse(window.localStorage.getItem('app_version'))
	if (!store.getters['check-app/app_version'] || parseInt(store.getters['check-app/app_version']) !== parseInt(appVersion)) {
    try {
      await store.dispatch('check-app/setAppVersion')
    } catch (e) { }
  }
  if (!store.getters['auth/check']) {
    next({ name: 'login' })
  } else {
    next()
  }
}

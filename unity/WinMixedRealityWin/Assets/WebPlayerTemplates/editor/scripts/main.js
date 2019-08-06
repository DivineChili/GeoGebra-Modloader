define(["require","exports","../scripts/editor","lib/function_helpers","lib/enums","lib/editor_settings","lib/editor_properties","../scripts/globals/prototype-methods","lib/key_handlers"],function(e,i,n,t,l,r,s,a,o){"use strict";Object.defineProperty(i,"__esModule",{value:!0}),a.default();var u=new n.coui.Editor;i.default=u,engine.on("LoadFile",function(e,i){console.log("Loading: "+e),"EmptyUI.html"!==e&&(window.CURRENT_FILE=e,document.body.dispatchEvent(new Event("turnOnLoadingScreen"))),setTimeout(function(){if(u.isItPublishPage(i)){var n=u.getOriginSceneUrl(i),r=t.default.getFileAndPath(e).path;return engine.call("SetCurrentSceneURL",r),u.PENDING_PUBLISH_PAGE_LOAD=!0,void u.openAsset({path:n})}u.PENDING_SCENE_LOAD=!1,u.PENDING_PUBLISH_PAGE_LOAD=!1;try{if(u.sceneExist(e))return u.focusFile(e),void console.log("File found. Focusing tab.")}catch(e){window.err=e,console.error("Could not open file: "+e)}try{if("EmptyUI.html"!==e){if(u.PENDING_PUBLISH_PAGE_LOAD&&""===i)return $("#coui-editor").trigger("vexFlashMessage",[l.default.Messages.cannotFindPublishPage]),void(u.PENDING_PUBLISH_PAGE_LOAD=!1);u.openFile(i,e),console.log("File loaded."),u.PENDING_WIDGET_LOAD&&(u.openFiles[u.selectedEditor].tab.tabWidgetState.editWidget=!0,u.PENDING_WIDGET_LOAD=!1),e.endsWith(".component")&&document.body.dispatchEvent(new Event("__openComponentComplete"))}else document.body.dispatchEvent(new Event("couiEditorIsReadyForUse"))}catch(e){console.error("Could not open file: "+e)}},100)}),engine.on("AboutToClose",function(){u.closeAllTabs()}),engine.on("FileSaved",u.onSaveCompleted.bind(u)),engine.on("SelectedFiles",function(e){if(0!==e.length){var i=e[0].split(/uiresources[\/\\]/i)[1],n=(i.replace(/.*[\\\/]/,""),/.+\\/),r="";new RegExp(n).test(i)&&(r=i.match(n)[0]);var s=r.replace(/\\/g,"/")||"",a=u.openFiles[u.selectedEditor];if(u.onSelectedFileType.publishPage){var o=""+a.tab.filePath+a.tab.filename;if(a.tab.filePath.replace(/\\/g,"/")!==s)return void $("#coui-editor").trigger("vexFlashMessage",[l.default.Messages.cannotSavePublishPage]);var c=t.default.removeSlashes([o,i]);if(c[0]===c[1])return void $("#coui-editor").trigger("vexFlashMessage",[l.default.Messages.duplicationFileName]);var g=a.runtimeEditor,d=u.adjustSavedContent(JSON.stringify(g.scene),{boxShadowAndClasses:!0,textarea:!0});g.runtimeLoad(d,{publishScene:!0,originalSceneName:a.tab.filename}).then(function(e){var n=u.rebuildUserHTML(e),t=u.cleanupHtmlExport(n);u.saveFile(i,t,u.selectedEditor),u.onSelectedFileType.publishPage=!1,a.tab.pendingSave=!1})}else{var p=void 0;u.sceneSettings?p=u.sceneSettings:(console.warn(l.default.warnMessages.sceneSettings),p={backgroundColor:"rgba(255, 255, 255, 0)",width:"1920",height:"1080",type:"aspectRatio16_9_full_hd"});var _=p.backgroundColor,y=p.width,m=p.height,F=p.type,f=t.default.getSceneSizeByType(F,y,m);u.createNewScene({style:{backgroundColor:_},sceneSize:f},i)}u.sceneSettings={}}}),engine.on("Ready",function(){u.init();var e=engine.call("prefs.get","preferences");if(null!==e.result)u.preferences=e.result,u.preferences.timeline?u.preferences.timeline.filterTimelineWidgets="true"===String(u.preferences.timeline.filterTimelineWidgets):u.preferences.timeline=r.default.defaultPreferences.timeline;else{u.preferences.couiEnvironment="GT";var i=r.default.defaultPreferences;engine.call("prefs.set","preferences",i),engine.call("prefs.save")}engine.call("RequestBackendInformation").then(function(e){vex.defaultOptions.className="vex-theme-flat-attack",localStorage.clear(),u.globalEditorInfo=e,Promise.all([System.import("lib/declarations"),System.import("lib/handlebars_helpers")]).then(function(i){u.EDITOR_VERSION=i[0].default.EDITOR_VERSION,u.Handlebars=i[1].default,u.attachEditorHandlers(),o.default.attachKeyHandlersGlobal(),e.backend!==l.default.Backends.Debug&&e.backend!==l.default.Backends.Website||u.attachBrowserHandlers();var n=s.default[r.default.environment[u.preferences.couiEnvironment]];u.environmentProperties=n;var t=n.DefaultExtensions;t="(."+(t=$.map(t,function(e){return[e].map(function(e){return e.join("|.")})}).join("|."))+")",u.listDirectory("",t,!0).then(function(e){u.updateSceneAssets(e)}),e.backend===l.default.Backends.Unreal&&$(".btn-pref-file").parent().remove(),u.initSceneConfigVex(),engine.trigger("FrontendReady")})})}),engine.mock("RequestBackendInformation",function(){return"localhost"===document.location.hostname?{backend:l.default.Backends.Debug}:{backend:l.default.Backends.Website}}),engine.mock("LaunchURL",function(e){return window.open(e,"_blank")}),engine.mock("Save",function(e,i){engine.trigger("FileSaved",e,e)}),engine.mock("ListDirectory",function(e,i,n){return"widgets"!==e?[{__Type:"FileEntry",isFile:!0,url:"videos/big-buck-bunny.webm"},{__Type:"FileEntry",isFile:!0,url:"images/mobaSliced2_04.png"},{__Type:"FileEntry",isFile:!0,url:"mobaSliced2_03.png"},{__Type:"FileEntry",isFile:!0,url:"images/mobaSliced2_07.png"},{__Type:"FileEntry",isFile:!0,url:"testStyle.css"},{__Type:"FileEntry",isFile:!0,url:"css/testStyle.css"},{__Type:"FileEntry",isFile:!0,url:"css/style.css"},{__Type:"FileEntry",isFile:!0,url:"hello.js"},{__Type:"FileEntry",isFile:!1,url:"videos"},{__Type:"FileEntry",isFile:!1,url:"images"},{__Type:"FileEntry",isFile:!0,url:"images/ammo.png"},{__Type:"FileEntry",isFile:!0,url:"images/blueBar.png"},{__Type:"FileEntry",isFile:!0,url:"images/bulletsG.png"},{__Type:"FileEntry",isFile:!0,url:"images/bulletsW.png"},{__Type:"FileEntry",isFile:!0,url:"images/corner.png"},{__Type:"FileEntry",isFile:!0,url:"images/crosshair.png"},{__Type:"FileEntry",isFile:!0,url:"images/gun.png"},{__Type:"FileEntry",isFile:!0,url:"images/hb.png"},{__Type:"FileEntry",isFile:!0,url:"images/hbar.png"},{__Type:"FileEntry",isFile:!0,url:"images/health.png"},{__Type:"FileEntry",isFile:!0,url:"images/hTitles.png"},{__Type:"FileEntry",isFile:!0,url:"images/leftHover.png"},{__Type:"FileEntry",isFile:!0,url:"images/mask.png"},{__Type:"FileEntry",isFile:!0,url:"images/minimap.png"},{__Type:"FileEntry",isFile:!0,url:"images/minimapB.png"},{__Type:"FileEntry",isFile:!0,url:"images/minimapR.png"},{__Type:"FileEntry",isFile:!0,url:"images/redCorner.png"},{__Type:"FileEntry",isFile:!0,url:"images/rightHover.png"},{__Type:"FileEntry",isFile:!0,url:"images/target.png"},{__Type:"FileEntry",isFile:!0,url:"images/titles.png"},{__Type:"FileEntry",isFile:!0,url:"images/tittle1.png"},{__Type:"FileEntry",isFile:!0,url:"images/whiteHover.png"},{__Type:"FileEntry",isFile:!0,url:"fonts/Exo2-SemiBoldItalic.ttf"},{__Type:"FileEntry",isFile:!0,url:"fonts/ArimaMadurai-Black.ttf"}]:[{__Type:"FileEntry",isFile:!0,url:"widgets/minimap.html"},{__Type:"FileEntry",isFile:!1,url:"widgets"}]}),engine.trigger("Ready")});
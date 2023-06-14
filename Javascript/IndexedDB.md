
# Store Clear
> 관리자 도구의 콘솔탭에서 입력 시 삭제 가능

```js
const databaseName = "EasyForm";
const DBOpenRequest = window.indexedDB.open(databaseName);

DBOpenRequest.onsuccess = (event) => {
  db = DBOpenRequest.result;
    
  // Clear all the data from the object store
  const storeName = "HISTORY";
  emptyStore(storeName);
};

async function emptyStore(storeName){
    const request = db.transaction([storeName], 'readwrite')
                      .objectStore(storeName)
                      .clear();

    request.onsuccess = ()=> {
        console.log(`Object Store ${storeName} emptied`);
    }

    request.onerror = (err)=> {
        console.error(`Error to empty Object Store: ${storeName}`)
    }
}
```
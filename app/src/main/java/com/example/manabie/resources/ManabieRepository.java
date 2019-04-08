package com.example.manabie.resources;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.manabie.resources.db.ManabieDao;
import com.example.manabie.resources.db.ManabieDatabase;
import com.example.manabie.resources.model.ManabieItem;
import com.example.manabie.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * ManabieRepository is class manage all resource included: local & remote
 */
public class ManabieRepository {
    private static final String TAG = ManabieRepository.class.getSimpleName();

    private ManabieDao manabieDao;
    private LiveData<List<ManabieItem>> mListManabie;

    public ManabieRepository(Application application) {
        ManabieDatabase database = ManabieDatabase.getInstance(application);
        manabieDao = database.manabieDao();
        mListManabie = manabieDao.getAllManabie();
    }

    public LiveData<List<ManabieItem>> getListManabie() {
        insertListManabie();
        return mListManabie;
    }

    private void insertListManabie() {
        getManabieObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<ManabieItem>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ManabieItem manabieItem) {
                        insertManabieToDb(manabieItem);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Failed when get list manabie: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                        Log.d(TAG, "Get list manabie success");
                    }
                });
    }

    private Observable<ManabieItem> getManabieObservable() {
        final List<ManabieItem> listManabie = dummyManabies();

        return Observable.create(new ObservableOnSubscribe<ManabieItem>() {
            @Override
            public void subscribe(ObservableEmitter<ManabieItem> emitter) throws Exception {

                for (ManabieItem manabieItem : listManabie) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(manabieItem);
                    }
                }

                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });
    }

    /**
     * Assume this method call data from network, our can not know how much time get it
     * @return list Manabie item
     */
    private List<ManabieItem> dummyManabies(){
        final List<ManabieItem> listManabie = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listManabie.add(new ManabieItem(i, AppUtil.getRandomColor(), 0));
        }
        return listManabie;
    }

    private void insertManabieToDb(final ManabieItem manabieItem) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                manabieDao.insert(manabieItem);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Insert manabie to DB success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "error when insert manabie: " + e.getMessage());
                    }
                });
    }

}

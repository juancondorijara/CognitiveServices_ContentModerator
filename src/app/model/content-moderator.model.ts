export class ContentModeratorModel {

  id?: number | undefined;

  adultClassificationScore?: number | undefined;
  isImageAdultClassified?: boolean | undefined;
  racyClassificationScore?: number | undefined;
  isImageRacyClassified?: boolean | undefined;
  resultBoolean?: boolean | undefined;

  url?: string | undefined;
  image?: string| undefined;
  resultString?: string| undefined;

  active?: boolean | undefined;

  constructor() {
    this.url = "";
  }
  
}
